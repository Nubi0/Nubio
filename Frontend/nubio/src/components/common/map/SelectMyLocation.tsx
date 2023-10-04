import { useDispatch } from "react-redux";
import { setStart, setStartName } from "../../../redux/slice/MapSlice";
import { MyLocationButton } from "../../../styles/SKakaoMap";

const SelectMyLocation = ({ removeMarker }: { removeMarker: () => void }) => {
  const dispatch = useDispatch();
  const startMyLocation = () => {
    dispatch(setStart({ x: window.myLongitude, y: window.myLatitude }));
    dispatch(setStartName("내 위치"));
    removeMarker();
    window.polyline?.setMap(null);
    window.startCustomOverlay?.setMap(null);

    let content = `<div class ="label"  style="background:#ffc542; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
        출발</span><span class="right"></span></div>`;
    // 커스텀 오버레이가 표시될 위치입니다
    let markerPosition = new kakao.maps.LatLng(
      window.myLatitude,
      window.myLongitude
    );
    // 커스텀 오버레이를 생성합니다
    let customOverlay = new kakao.maps.CustomOverlay({
      position: markerPosition,
      content: content,
    });
    window.startCustomOverlay = customOverlay;
    // 커스텀 오버레이를 지도에 표시합니다
    window.startCustomOverlay.setMap(window.map);
  };
  return (
    <MyLocationButton onClick={startMyLocation}>
      내 위치를 출발지로
    </MyLocationButton>
  );
};

export default SelectMyLocation;
