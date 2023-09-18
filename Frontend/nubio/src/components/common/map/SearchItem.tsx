import { useState } from "react";
import {
  DirectionButtonWrapper,
  ItemInfoWrapper,
  ItemNameWrapper,
  SearchItemWrapper,
} from "../../../styles/SKakaoMap";
import axios from "axios";

import { placeType } from "../../../types/kakaoMaps";

const SearchItem = ({ place, index }: { place: placeType; index: number }) => {
  const apiKey = "5b3ce3597851110001cf6248b9e727b35dd741be8d930a1f0a2c3b2e";

  const onClickStart = () => {
    localStorage.setItem("startPoint", `${place.x},${place.y}`);
    localStorage.setItem("startName", `${place.place_name}`);
  };
  const onClickEnd = () => {
    localStorage.setItem("endPoint", `${place.x},${place.y}`);
    localStorage.setItem("endtName", `${place.place_name}`);
  };
  const startPoint = localStorage.getItem("startPoint");
  const endPoint = localStorage.getItem("endPoint");
  const getDirection = () => {
    axios
      .get(
        `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${startPoint}&end=${endPoint}`
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <SearchItemWrapper>
      <ItemNameWrapper>
        <h5>
          {index + 1}. {place.place_name}
        </h5>
        <a id="homePage" href={place.place_url}>
          상세보기
        </a>
      </ItemNameWrapper>
      <ItemInfoWrapper>
        {place.road_address_name ? (
          <p className="address">{place.road_address_name}</p>
        ) : (
          <p className="address">{place.address_name}</p>
        )}
        <p className="tel">{place.phone}</p>
      </ItemInfoWrapper>
      <DirectionButtonWrapper>
        <button onClick={onClickStart}>출발</button>
        <button onClick={onClickEnd}>도착</button>
        <button onClick={getDirection}>고</button>
      </DirectionButtonWrapper>
    </SearchItemWrapper>
  );
};

export default SearchItem;
