import React, { useState } from "react";
import axios from "axios";
import { SearchBarWrapper, SearchForm } from "../../../styles/SSearch";
import SearchResults from "./SearchResults";
interface SearchResult {
  name: string;
  // 다른 필요한 속성들을 여기에 추가할 수 있습니다.
}

const SearchBar = () => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  const [keyword, setKeyword] = useState<string>("");
  const [results, setResults] = useState<SearchResult[]>([]);
  const [currentPage, setCurrentPage] = useState(1);

  const search = async () => {
    try {
      const data = {
        searchKeyword: keyword,
        resCoordType: "EPSG3857",
        reqCoordType: "WGS84GEO",
        count: 10,
        page: currentPage,
      };
      const headers = {
        appKey: "prZbuvPsM53ADwzJMIxl13StkVuNvAG86O6n4YhF",
      };

      const response = await axios.get(
        "https://apis.openapi.sk.com/tmap/pois",
        {
          params: data,
          headers: headers,
        }
      );

      const newResults = response.data.searchPoiInfo.pois.poi;

      setResults((prevResults: SearchResult[]) => [
        ...prevResults,
        ...newResults,
      ]);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault(); // 이벤트의 기본 동작 중지
    setCurrentPage(1);
    setResults([]);
    search();
  };

  const handleLoadMore = () => {
    setCurrentPage((prevPage: number) => prevPage + 1);
    search();
  };
  return (
    <SearchBarWrapper>
      <SearchForm onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="장소를 검색하세요"
          onChange={(e) => setKeyword(e.target.value)}
        />
        <img
          src={searchIcon}
          alt="돋보기"
          className="
readingGlasses"
        />
      </SearchForm>
      <SearchResults results={results} />
      {/* <ul>
        {results.map((result, index) => (
          <li key={index}>{result.name}</li>
        ))}
      </ul> */}

      {/* {results.length > 0 && (
        <button onClick={handleLoadMore}>더 불러오기</button>
      )} */}
    </SearchBarWrapper>
  );
};
export default SearchBar;
