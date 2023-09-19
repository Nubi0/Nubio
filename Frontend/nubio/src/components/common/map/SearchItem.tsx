import {
  DirectionButtonWrapper,
  ItemInfoWrapper,
  ItemNameWrapper,
  SearchItemWrapper,
} from "../../../styles/SKakaoMap";
interface SearchResult {
  name: string;
  // 다른 필요한 속성들을 여기에 추가할 수 있습니다.
}

const SearchItem = ({ place }: { place: SearchResult }) => {
  // const onClickStart = () => {
  //   localStorage.setItem("startPoint", `${place.x},${place.y}`);
  //   localStorage.setItem("startName", `${place.place_name}`);
  // };
  // const onClickEnd = () => {
  //   localStorage.setItem("endPoint", `${place.x},${place.y}`);
  //   localStorage.setItem("endtName", `${place.place_name}`);
  // };

  return (
    <SearchItemWrapper>
      <ItemNameWrapper>
        {/* <a id="homePage" href={place.place_url}> */}
        {/* 상세보기 */}
        {/* </a> */}
      </ItemNameWrapper>
      <ItemInfoWrapper>{place.name}</ItemInfoWrapper>
      <DirectionButtonWrapper>
        {/* <button onClick={onClickStart}>출발</button> */}
        {/* <button onClick={onClickEnd}>도착</button> */}
      </DirectionButtonWrapper>
    </SearchItemWrapper>
  );
};

export default SearchItem;
