import { useSelector } from "react-redux";
import RootState from "../../../types/RootState";
import KakaoMap from "./KakaoMap";

const Map = () => {
  const position = useSelector((state: RootState) => state.enjoy.positions);
  return (
    <>
      <KakaoMap position={position} />
    </>
  );
};

export default Map;
