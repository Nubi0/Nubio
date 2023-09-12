import { useState } from "react";
import { SearchBarWrapper } from "../../../styles/SSearchBar";
import SearchList from "./searchList/SearchList";

const SearchBar = () => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  const [isSearch, setIsSearch] = useState(false);
  const test = () => {
    setIsSearch(!isSearch);
  };
  return (
    <>
      {isSearch ? (
        <SearchBarWrapper>
          <input type="text" placeholder="장소를 검색하세요" />
          <img
            onClick={test}
            src={searchIcon}
            alt="돋보기"
            className="
readingGlasses"
          />
          <SearchList />
        </SearchBarWrapper>
      ) : (
        <SearchBarWrapper>
          <input type="text" placeholder="장소를 검색하세요" />
          <img
            onClick={test}
            src={searchIcon}
            alt="돋보기"
            className="
readingGlasses"
          />
        </SearchBarWrapper>
      )}
    </>
  );
};

export default SearchBar;
