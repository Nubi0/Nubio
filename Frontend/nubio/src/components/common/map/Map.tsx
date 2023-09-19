import { useSelector } from "react-redux";
import RootState from "../../../types/RootState";
import KakaoMap from "./KakaoMap";
import Search from "../search/Search";
import TMap from "./TMap";

const Map = () => {
  const searchKeyword = useSelector((state: RootState) => state.search);
  const position = useSelector((state: RootState) => state.enjoy.positions);
  return (
    <>
      <Search />
      <TMap />
    </>
  );
};

export default Map;
