import { SearchBarWrapper } from "../../../styles/SSearchBar";
import SearchList from "./searchList/SearchList";

const SearchBar = () => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";

  return (
    <SearchBarWrapper>
      <input type="text" placeholder="장소를 검색하세요" />
      <img
        src={searchIcon}
        alt="돋보기"
        className="
readingGlasses"
      />
      <SearchList />
    </SearchBarWrapper>
  );
};

export default SearchBar;
