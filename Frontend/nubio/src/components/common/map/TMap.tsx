import React, { useEffect } from "react";
import { TMapWrapper } from "../../../styles/STMap";

const { Tmapv2 } = window as any;

declare global {
  interface Window {
    Tmapv2: any;
  }
}

const TMap = () => {
  let map: any;
  let marker: any;
  let markers: any[] = [];
  let lonlat: any;

  // TMap 설정
  const initTmap = () => {
    map = new Tmapv2.Map("map_div", {
      center: new Tmapv2.LatLng(37.566481622437934, 126.98502302169841),
      width: "100%",
      height: "100%",
      zoom: 15,
    });

    map.addListener("click", onClick);
  };

  const removeMarkers = () => {
    for (let i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  };
  // 맵 클릭하면 마커 생성
  const onClick = (e: any) => {
    removeMarkers();
    lonlat = e.latLng;
    marker = new Tmapv2.Marker({
      position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()),
      map: map,
    });

    markers.push(marker);
  };

  useEffect(() => {
    initTmap();
  }, []);

  return <TMapWrapper id="map_div"></TMapWrapper>;
};

export default TMap;
