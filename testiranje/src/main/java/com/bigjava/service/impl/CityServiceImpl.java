package com.bigjava.service.impl;

import com.bigjava.api.dto.city.CityDtoRequest;
import com.bigjava.api.dto.city.CitySerach;
import com.bigjava.api.dto.comment.CommentDtoRequest;
import com.bigjava.api.dto.city.CityDtoResponse;
import com.bigjava.api.dto.document.Response;
import com.bigjava.constants.Constants;
import com.bigjava.entity.*;
import com.bigjava.exception.CityException;
import com.bigjava.exception.CommnetException;
import com.bigjava.mapper.AirPortMapper;
import com.bigjava.mapper.CityMapper;
import com.bigjava.repository.CityRepository;
import com.bigjava.repository.CommentRepository;
import com.bigjava.repository.CountryRepository;
import com.bigjava.repository.RouteRepository;
import com.bigjava.service.CityService;
import com.bigjava.service.algorithm.GraphWeighted;
import com.bigjava.service.algorithm.NodeWeighted;
import com.bigjava.service.algorithm.ResponseFlight;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dbogicevic
 */
@Service
@AllArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;
    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private AirPortMapper airPortMapper;

    @Override
    @Transactional
    public CityDtoResponse createCity(CityDtoRequest cityDtoRequest) {
        Optional<Country> countryDataBase = countryRepository.findByCountryName(cityDtoRequest.getCountryName());
        Optional<City> cityDatabase = cityRepository.findByCityName(cityDtoRequest.getCityName());
        if (cityDatabase.isPresent()) {
            throw new CityException("City exist");
        }
        City city = cityMapper.toCity(cityDtoRequest);
        if (!countryDataBase.isPresent()) {
            Country country = new Country();
            country.setCountryName(cityDtoRequest.getCountryName());
            countryRepository.save(country);
            city.setCountry(country);
        } else {
            city.setCountry(countryDataBase.get());
        }
        return cityMapper.toCityResponse(cityRepository.save(city));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDtoResponse> sreachCity(CitySerach citySerach, Integer limit) {
        List<CityDtoResponse> cityDtoResponses = new ArrayList<>();
        List<City> cities = cityRepository.findByCityNameContaining(citySerach.getCityName());
        if (limit == 0) {
            cityDtoResponses = cities.stream()
                    .map(cityMapper::toCityResponse)
                    .collect(Collectors.toList());

        } else {
            for (City city : cities) {
                CityDtoResponse cityDtoResponse = cityMapper.toCityResponse(city);
                List<Comment> comments = commentRepository.findByCity(city, PageRequest.of(0, limit));
                cityDtoResponse.setComments(comments);
                cityDtoResponses.add(cityDtoResponse);

            }

        }

        return cityDtoResponses;
    }

    private Comment findByComment(Long idComment) {
        return commentRepository.findById(idComment)
                .orElseThrow(() -> new CommnetException("Comment not exist"));
    }

    @Transactional
    public void updateComment(Long idComment, CommentDtoRequest commentDtoRequest) {
        Comment comment = findByComment(idComment);
        comment.setDescription(commentDtoRequest.getDescription());
        commentRepository.save(comment);

    }

    @Transactional
    public void deleteComment(Long idComment) {
        Comment comment = findByComment(idComment);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public Comment createComment(Long idCity, CommentDtoRequest commentDtoRequest) {
        City city = cityRepository.findById(idCity)
                .orElseThrow(() -> new CityException("City not exist"));
        Comment comment = cityMapper.toComment(commentDtoRequest);
        city.addComment(comment);
        return commentRepository.save(comment);

    }

    public Response findByCheapestFlight(String from, String to) {
        long pocetak = System.currentTimeMillis();
        GraphWeighted graphWeighted = new GraphWeighted(true);
        Set<NodeWeighted> nodeWeightedSet = new HashSet<>();
        List<Route> route = routeRepository.finaAllRoute();
        long kraj = System.currentTimeMillis();
        log.info("Posle upita iz baze " + (kraj - pocetak));
        route.forEach(r -> {
            if (nodeWeightedSet.contains(new NodeWeighted(r.getRoutePk().getSourceCode())))
                nodeWeightedSet.add(new NodeWeighted(r.getRoutePk().getSourceCode()));
            if (nodeWeightedSet.contains(new NodeWeighted(r.getRoutePk().getDestinationCode())))
                nodeWeightedSet.add(new NodeWeighted(r.getRoutePk().getDestinationCode()));

            Optional<NodeWeighted> nodeWeighted = nodeWeightedSet.parallelStream().filter(a -> a.getName().equals(r.getRoutePk().getSourceCode())).findFirst();
            Optional<NodeWeighted> nodeWeighted1 = nodeWeightedSet.parallelStream().filter(a -> a.getName().equals(r.getRoutePk().getDestinationCode())).findFirst();
            graphWeighted.addEdge(nodeWeighted.get(), nodeWeighted1.get(), r.getPrice());


        });
        kraj = System.currentTimeMillis();
        log.info("Posle prve metode " + (kraj - pocetak));
        Optional<NodeWeighted> nodeWeighted = nodeWeightedSet.stream().filter(a -> a.getName().equals(from)).findFirst();
        Optional<NodeWeighted> nodeWeighted1 = nodeWeightedSet.stream().filter(a -> a.getName().equals(to)).findFirst();
        kraj = System.currentTimeMillis();
        log.info("Pre ifa " + (kraj - pocetak));
        if (nodeWeighted.isPresent() && nodeWeighted1.isPresent()) {
            ResponseFlight responseFlight = graphWeighted.dijkstraShortestPath(nodeWeighted.get(), nodeWeighted1.get());
            return Response.builder()
                    .code(Constants.OK)
                    .description("Ok")
                    .content(airPortMapper.toTravel(getValue(responseFlight), responseFlight))
                    .build();
        }

        return Response.builder()
                .description("No result")
                .content(new ResponseFlight())
                .build();
    }

    private List<Airport> getValue(ResponseFlight responseFlight) {
        List<String> paths = Arrays.stream(responseFlight.getPath().split(" ")).collect(Collectors.toList());
        List<RoutePk> routePkList = new ArrayList<>();
        for (int i = 0; i < paths.size() - 1; i++) {
            routePkList.add(new RoutePk(paths.get(i), paths.get(i + 1)));
        }
        List<Route> routes = routeRepository.findByRoutePkAndDestinationCode(routePkList);
        return routes.stream().map(r -> r.getDestinationAirPort()).collect(Collectors.toList());

    }

}
