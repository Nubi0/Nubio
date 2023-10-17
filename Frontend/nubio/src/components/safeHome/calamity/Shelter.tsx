import { ShelterButton } from "../../../styles/SSafeHomePage";
import { useDispatch, useSelector } from "react-redux";
import { setMarkerList, setShowShelters } from "../../../redux/slice/SafeSlice";
import axios from "axios";

const Shelter = () => {
  const dispatch = useDispatch();
  const showShelters = useSelector(
    (state: { map: { showShelters: false } }) => state.map.showShelters,
  );
  const markerList = useSelector(
    (state: { map: { markerList: Array<any> } }) => state.map.markerList,
  );
  const messageMarkerList = useSelector(
    (state: { map: { messageMarkerList: Array<any> } }) =>
      state.map.messageMarkerList,
  );
  const GetNearbyShelter = () => {
    axios
      .get(
        `${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/nearwith/safe-shelter/all?longitude=${window.myLongitude}&latitude=${window.myLatitude}&distance=1`,
      )
      .then((res) => {
        // console.log(res.data.data.content);
        dispatch(setShowShelters(true));
        const shelter = res.data.data.content;
        const newmarkerList: any = [];
        for (let i = 0; i < shelter.length; i++) {
          let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
                ${shelter[i].name}</span><span class="right"></span></div>`;
          let markerPosition = new kakao.maps.LatLng(
            shelter[i].location.latitude,
            shelter[i].location.longitude,
          );
          let customOverlay = new kakao.maps.CustomOverlay({
            position: markerPosition,
            content: content,
          });
          window.shelterCustomOverlay = customOverlay;
          customOverlay.setMap(window.map);
          newmarkerList.push(customOverlay);
        }
        dispatch(setMarkerList(newmarkerList));
      })
      .catch((err) => {
        // console.log(err);
      });
  };
  const DeleteShelter = () => {
    dispatch(setShowShelters(false));
    for (let i = 0; i < markerList.length; i++) {
      markerList[i].setMap(null);
    }
    for (let j = 0; j < messageMarkerList.length; j++) {
      messageMarkerList[j].setMap(null);
    }
  };
  return (
    <>
      {showShelters ? (
        <ShelterButton onClick={DeleteShelter}>대피소 가리기</ShelterButton>
      ) : (
        <ShelterButton onClick={GetNearbyShelter}>대피소 찾기</ShelterButton>
      )}
    </>
  );
};

export default Shelter;
