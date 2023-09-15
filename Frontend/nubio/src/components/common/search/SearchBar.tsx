import { useState } from "react";
import { SearchBarWrapper, SearchForm } from "../../../styles/SSearchBar";
import SearchList from "./searchList/SearchList";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from "react-redux";
import { setKeyword } from "../../../redux/slice/KakaoMap";
import { RootState } from "../../../types/RootState"; // RootState를 불러옵니다.

const SearchBar = () => {
  const dispatch = useDispatch();
  const inputValue = useSelector((state: RootState) => state.keywordSearch);
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  const [isSearch, setIsSearch] = useState(false);
  const test = () => {
    setIsSearch(!isSearch);
  };

  // 키워드 검색
  // 입력 폼 변화 감지하여 입력 값 관리
  const [value, setValue] = useState("");
  // 제출한 검색어 관리

  // 입력 폼 변화 감지하여 입력 값을 state에 담아주는 함수
  const keywordChange = (e: {
    preventDefault: () => void;
    target: { value: string };
  }) => {
    e.preventDefault();
    setValue(e.target.value);
  };

  // 제출한 검색어 state에 담아주는 함수
  const submitKeyword = (e: { preventDefault: () => void }) => {
    e.preventDefault();
    if (value === "") {
      Swal.fire({
        position: "center",
        title: "검색어를 입력해주세요.",
        text: "NUBIO",
        timer: 1500,
        color: "black",
      });
    }
    dispatch(setKeyword(value));
    console.log(value);
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
          <SearchForm onSubmit={submitKeyword}>
            <input
              type="text"
              placeholder="장소를 검색하세요"
              onChange={keywordChange}
            />
            <img
              onClick={test}
              src={searchIcon}
              alt="돋보기"
              className="
readingGlasses"
            />
          </SearchForm>
        </SearchBarWrapper>
      )}
    </>
  );
};

export default SearchBar;
