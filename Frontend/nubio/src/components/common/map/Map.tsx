import { useSelector } from "react-redux";
import KakaoMap from "./KakaoMap";

const Map = () => {
  const position = useSelector((state: {enjoy: {positions: placeItem[]}}) => state.enjoy.positions);
  return (
    <>
      <KakaoMap position={position} />
    </>
  );
};

export default Map;
