import {
  SearchResultsWrapper,
  SetDirectionWrapper,
  SearchListWrapper,
} from "../../../styles/SSearch";
import SearchItem from "../map/SearchItem";

interface SearchResult {
  name: string;
  // 다른 필요한 속성들을 여기에 추가할 수 있습니다.
}
interface SearchResultsProps {
  results: SearchResult[]; // SearchResult 타입 배열
}
const SearchResults: React.FC<SearchResultsProps> = ({ results }) => {
  console.log(results);
  return (
    <SearchResultsWrapper>
      {/* <SetDirection  /> */}
      <SetDirectionWrapper>
        <input type="text" placeholder="출발지" />
        <input type="text" placeholder="도착지" />
        <button>길 찾기</button>
      </SetDirectionWrapper>
      <p>검색 결과</p>
      {/* <SearchListWrapper>
        <ul id="places-list">
          {results.map((item, index) => (
            <SearchItem key={index} place={item} />
          ))}
        </ul>
      </SearchListWrapper> */}
      <div id="pagination"></div>
    </SearchResultsWrapper>
  );
};
export default SearchResults;
