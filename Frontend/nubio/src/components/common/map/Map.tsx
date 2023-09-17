import { useSelector } from 'react-redux';
import RootState from '../../../types/RootState';
import KakaoMap from './KakaoMap';
import SearchBar from '../search/SearchBar';

const Map = () => {
  const searchKeyword = useSelector((state: RootState) => state.search);
  const position = useSelector((state: RootState) => state.enjoy.positions);
  return (
    <>
      {' '}
      <SearchBar />
      <KakaoMap searchKeyword={searchKeyword} position={position} />
    </>
  );
};

export default Map;
