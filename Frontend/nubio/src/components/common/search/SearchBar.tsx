// Hook
import { useState } from "react";
import { useDispatch } from "react-redux";
// 라이브러리
import Swal from "sweetalert2";
// 컴포넌트
// 스타일
import { SearchBarWrapper, SearchForm } from "../../../styles/SSearch";
// redux
import { setkeyWord } from "../../../redux/slice/MapSlice";

const SearchBar = ({ searchPlaces, setListIsOpen, setFindRouteOpen }: any) => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  // 검색
  const dispatch = useDispatch();

  // 입력 폼 변화 감지하여 입력 값 관리
  const [Value, setValue] = useState("");

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
    dispatch(setkeyWord(Value));
    if (Value == "") {
      Swal.fire({
        title: "검색어를 입력해주세요.",
      });
    }
    searchPlaces(Value);
    setFindRouteOpen(false);
  };
  // 검색리스트 보기
  const inputClick = () => {
    setListIsOpen(true);
    searchPlaces(Value);
  };
  return (
    <SearchBarWrapper>
      <SearchForm onSubmit={submitKeyword}>
        <input
          type="text"
          placeholder="장소를 검색하세요"
          onChange={keywordChange}
          onClick={inputClick}
        />
        <img
          src={searchIcon}
          alt="돋보기"
          className="
readingGlasses"
        />
      </SearchForm>
    </SearchBarWrapper>
  );
};
export default SearchBar;
