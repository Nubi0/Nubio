import { ShelterButton } from "../../../styles/SSafeHomePage";
import { useSelector } from "react-redux";
import { DeleteShelter, GetNearbyShelter } from "../../../util/GetShelter";
const Shelter = () => {
  const showShelters = useSelector(
    (state: { map: { showShelters: any } }) => state.map.showShelters
  );
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
