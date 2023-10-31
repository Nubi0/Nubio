import { useSelector } from "react-redux";
import {
  ClearRouteButton,
  DestinationWrapper,
  SearchListWrapper,
  SearchResultsWrapper,
} from "../../../styles/SMap";
import SafeDirection from "../../safeHome/route/safe/SafeDirection";
import ShortDirection from "../../safeHome/route/short/ShortDirection";
import SelectMyLocation from "../map/SelectMyLocation";
import { setEndName, setStartName } from "../../../redux/slice/MapSlice";
import { setSafeTime, setShortTime } from "../../../redux/slice/SafeSlice";

const SearchResult = ({
  searchKeyword,
  removeMarker,
  setFindRouteOpen,
}: any) => {
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

  return (
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
      <ClearRouteButton onClick={clearAllRoute}>경로 지우기</ClearRouteButton>
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
  );
};

export default SearchResult;
function dispatch(arg0: any) {
  throw new Error("Function not implemented.");
}
