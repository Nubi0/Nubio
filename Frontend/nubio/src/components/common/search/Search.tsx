import { useDispatch, useSelector } from "react-redux";

import {
  setEnd,
  setEndName,
  setListIsOpen,
  setSearchMarker,
  setStart,
  setStartName,
} from "@redux/slice/MapSlice";
import { useState } from "react";

import Swal from "sweetalert2";
import { useLocation } from "react-router-dom";
import SearchBar from "./SearchBar";
import {
  ClearRouteButton,
  DestinationWrapper,
  SearchListWrapper,
  SearchResultsWrapper,
} from "@styles/SMap";

import ShortDirection from "@components/safeHome/route/short/ShortDirection";
import SafeDirection from "@components/safeHome/route/safe/SafeDirection";
import { setSafeTime, setShortTime } from "@redux/slice/SafeSlice";

import RouteInfo from "@components/safeHome/route/RouteInfo";

import SelectMyLocation from "../map/SelectMyLocation";
import { setPosition } from "@redux/slice/EnjoySlice";

const Search = () => {
  var markerList: any = [];
  const location = useLocation();
  const dispatch = useDispatch();
  const searchKeyword = useSelector(
    (state: { map: { keyWord: string } }) => state.map.keyWord,
  );
  // state
  const listIsOpen = useSelector(
    (state: { map: { listIsOpen: any } }) => state.map.listIsOpen,
  );
  console.log(listIsOpen);
  const [findRouteOpen, setFindRouteOpen] = useState(false);
  // 검색 reudx
  const startName = useSelector(
    (state: { map: { startName: string } }) => state.map.startName,
  );
  const endName = useSelector(
    (state: { map: { endName: string } }) => state.map.endName,
  );
  const safePlaces = useSelector(
    (state: { safe: { safePlace: any } }) => state.safe.safePlace,
  );
  const safeMarkerList = useSelector(
    (state: { safe: { safeMarkerList: Array<any> } }) =>
      state.safe.safeMarkerList,
  );

  // 라인, 마커 삭제
  const clearRoute = () => {
    for (let j = 0; j < safeMarkerList.length; j++) {
      safeMarkerList[j].setMap(null);
    }
    window.polyline?.setMap(null);
    window.safeCustomOverlay?.setMap(null);
  };
  const removeSafeMarker = () => {
    for (let i = 0; i < safePlaces.length; i++) {
      window.safeCustomOverlay.setMap(null);
    }
  };
  // 맵에 표시된 경로 관련 삭제
  const clearAllRoute = () => {
    removeMarker();
    clearRoute();
    window.endCustomOverlay?.setMap(null);
    window.startCustomOverlay?.setMap(null);
    dispatch(setStartName(""));
    dispatch(setEndName(""));
    dispatch(setShortTime(null));
    dispatch(setSafeTime(null));
    setFindRouteOpen(false);
    removeSafeMarker();
  };
  // 키워드 검색을 요청하는 함수
  function searchPlaces(keyword: string) {
    dispatch(setListIsOpen(true));
    window.ps.keywordSearch(keyword, placesSearchCB);
  }
  // 장소검색이 완료됐을 때 호출되는 콜백함수
  function placesSearchCB(data: string, status: any, pagination: any) {
    if (status === kakao.maps.services.Status.OK) {
      displayPlaces(data);
      displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      Swal.fire({
        title: "검색 결과가 존재하지 않습니다.",
        text: "Nubio",
      });
      hideSearchResult();
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      Swal.fire({
        title: "검색 중 오류가 발생했습니다.",
        text: "Nubio",
      });
      return;
    }
  }

  // 검색 결과 목록과 마커를 표출하는 함수
  function displayPlaces(places: string | any[]) {
    removeMarker();
    console.log(markerList);
    const listEl = document.getElementById("places-list"),
      resultEl = document.getElementById("search-result"),
      fragment = document.createDocumentFragment(),
      bounds = new window.kakao.maps.LatLngBounds();
    listEl && removeAllChildNods(listEl);
    for (var i = 0; i < places.length; i++) {
      let placePosition = new window.kakao.maps.LatLng(
          places[i].y,
          places[i].x,
        ),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]);
      bounds.extend(placePosition);

      (function (marker, title) {
        itemEl.onclick = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
          window.infowindow.close();
        };
      })(marker, places[i].place_name);

      fragment.appendChild(itemEl);
    }
    console.log(markerList);
    listEl && listEl.appendChild(fragment);
    if (resultEl) {
      resultEl.scrollTop = 0;
    }
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정
    window.map.setBounds(bounds);
  }

  // 검색결과 항목을 Element로 반환하는 함수
  function getListItem(index: number, places: placeType) {
    const el = document.createElement("li");
    const onClickStart = () => {
      dispatch(setStart({ x: places.x, y: places.y }));
      dispatch(setStartName(places.place_name));
      window.polyline?.setMap(null);
      window.startCustomOverlay?.setMap(null);
      let content = `<div class ="label"  style="background:#ffc542; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
      출발</span><span class="right"></span></div>`;
      let markerPosition = new window.kakao.maps.LatLng(places.y, places.x);
      let customOverlay = new window.kakao.maps.CustomOverlay({
        position: markerPosition,
        content: content,
      });
      window.startCustomOverlay = customOverlay;
      window.startCustomOverlay.setMap(window.map);
    };
    const onClickEnd = () => {
      dispatch(setEnd({ x: places.x, y: places.y }));
      dispatch(setEndName(places.place_name));
      removeMarker();
      window.polyline?.setMap(null);
      window.endCustomOverlay?.setMap(null);

      let content = `<div class ="label" style="background:#f9373f; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"><span class="left"></span><span class="center">도착</span><span class="right"></span></div>`;
      let markerPosition = new window.kakao.maps.LatLng(places.y, places.x);
      let customOverlay = new window.kakao.maps.CustomOverlay({
        position: markerPosition,
        content: content,
      });

      window.endCustomOverlay = customOverlay;
      window.endCustomOverlay.setMap(window.map);
    };
    const ClickPlace = (place: placeType) => {
      dispatch(setPosition(place));
    };
    if (places.length !== 0) {
      const isEnjoy = location.pathname.includes("/enjoy");
      let itemStr = `
        <div class="info">
        <div class="name">
            <h3 class="info-item place-name">${index + 1}. ${
        places.place_name
      }<a id="homePage" href=${places.place_url}>상세보기</a>
      </h3>
        
    </div>
            ${
              places.road_address_name
                ? `<span class="address ">
                  ${places.road_address_name}
                    ${places.address_name}
                    </span>`
                : `<span class="address ">
                    ${places.address_name}
                </span>`
            }
            <span class="tel">
              ${places.phone}
            </span>
            <div class="bnt">
              <button id="start">출발</button>
              <button id="end">도착</button>
            </div>
          </a>
        </div>
        `;
      el.innerHTML = itemStr;
      el.className = "item";
      const startButton = el.querySelector("#start");
      startButton?.addEventListener("click", onClickStart);
      const endButton = el.querySelector("#end");
      endButton?.addEventListener("click", onClickEnd);
      if (isEnjoy) {
        const select = el.querySelector(".place-name");
        select?.addEventListener("click", () => ClickPlace(places));
      }
    }

    return el;
  }
  // 마커를 생성하고 지도 위에 마커를 표시하는 함수
  function addMarker(position: any, idx: number) {
    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png",
      imageSize = new window.kakao.maps.Size(36, 37),
      imgOptions = {
        spriteSize: new window.kakao.maps.Size(36, 691),
        spriteOrigin: new window.kakao.maps.Point(0, idx * 46 + 10),
        offset: new window.kakao.maps.Point(13, 37),
      },
      markerImage = new window.kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imgOptions,
      );
    const marker = new kakao.maps.Marker({
      position: position,
      image: markerImage,
    });

    window.searchMarkers = marker;
    window.searchMarkers.setMap(window.map);
    markerList.push(marker);
    return marker;
  }

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  const removeMarker = () => {
    console.log(markerList);
    for (let i = 0; i < markerList.length; i++) {
      window.searchMarkers = markerList[i];
      window.searchMarkers.setMap(null);
      markerList[i].setMap(null);
      console.log(window.searchMarkers);
    }
    markerList = [];
  };

  // 검색결과 목록 하단에 페이지번호를 표시는 함수
  function displayPagination(pagination: {
    last: number;
    current: number;
    gotoPage: (arg0: number) => void;
  }) {
    const paginationEl = document.getElementById("pagination") as HTMLElement;
    let fragment = document.createDocumentFragment();
    let i;
    while (paginationEl.hasChildNodes()) {
      paginationEl.lastChild &&
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      const el = document.createElement("a") as HTMLAnchorElement;
      el.href = "#";
      el.innerHTML = i.toString();

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  }

  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수
  function displayInfowindow(marker: any, title: string) {
    const content =
      '<div style="padding:5px;z-index:1;" class="marker-title">' +
      title +
      "</div>";
    window.infowindow.setContent(content);
    window.infowindow.open(window.map, marker);
  }

  // 검색결과 목록의 자식 Element를 제거하는 함수
  function removeAllChildNods(el: HTMLElement) {
    while (el.hasChildNodes()) {
      el.lastChild && el.removeChild(el.lastChild);
    }
  }
  // 검색 결과가 없을 때는 search-result 요소를 숨기는 함수
  function hideSearchResult() {
    const searchResult = document.getElementById("search-result");
    if (searchResult) {
      searchResult.style.display = "none";
    }
  }

  return (
    <>
      <SearchBar
        searchPlaces={searchPlaces}
        setFindRouteOpen={setFindRouteOpen}
        removeMarker={removeMarker}
      />
      {findRouteOpen && listIsOpen ? <RouteInfo /> : null}
      {searchKeyword !== "" && listIsOpen ? (
        <SearchResultsWrapper id="search-result">
          <p className="result-text">
            {searchKeyword}
            검색 결과
            <SelectMyLocation removeMarker={removeMarker} />
          </p>
          <DestinationWrapper>
            <h4>출발지 : {startName}</h4>
            <h4>도착지 : {endName}</h4>
          </DestinationWrapper>
          <ClearRouteButton onClick={clearAllRoute}>
            경로 지우기
          </ClearRouteButton>
          <ShortDirection
            clearRoute={clearRoute}
            setFindRouteOpen={setFindRouteOpen}
          />
          <SafeDirection
            clearRoute={clearRoute}
            setFindRouteOpen={setFindRouteOpen}
          />
          <SearchListWrapper className="scroll-wrapper">
            <ul id="places-list"></ul>
          </SearchListWrapper>
          <div id="pagination"></div>
        </SearchResultsWrapper>
      ) : (
        <SearchResultsWrapper id="search-result" style={{ display: "none" }}>
          <p className="result-text">
            {searchKeyword}
            검색 결과
            <SelectMyLocation removeMarker={removeMarker} />
          </p>
          <DestinationWrapper>
            <h4>출발지 : {startName}</h4>
            <h4>도착지 : {endName}</h4>
          </DestinationWrapper>
          <ClearRouteButton onClick={clearAllRoute}>
            경로 지우기
          </ClearRouteButton>
          <ShortDirection
            clearRoute={clearRoute}
            setFindRouteOpen={setFindRouteOpen}
          />
          <SafeDirection
            clearRoute={clearRoute}
            setFindRouteOpen={setFindRouteOpen}
          />
          <SearchListWrapper className="scroll-wrapper">
            <ul id="places-list"></ul>
          </SearchListWrapper>
          <div id="pagination"></div>
        </SearchResultsWrapper>
      )}
    </>
  );
};
export default Search;
