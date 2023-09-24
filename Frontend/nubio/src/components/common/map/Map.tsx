import { useSelector } from "react-redux";
import RootState from "../../../types/RootState";
import KakaoMap from "./KakaoMap";

const Map = () => {
  const searchKeyword = useSelector((state: RootState) => state.search);
  const position = useSelector((state: RootState) => state.enjoy.positions);
  return (
    <>
      <KakaoMap searchKeyword={searchKeyword} position={position} />
    </>
  );
};

export default Map;
