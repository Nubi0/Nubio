import { useState } from "react";
import { useDispatch } from "react-redux";
import { inputKeyword } from "../../../redux/slice/KakaoSlice";
import Swal from "sweetalert2";
import { SearchBarWrapper, SearchForm } from "../../../styles/SSearch";

const SearchBar = () => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  // 검색
  const dispatch = useDispatch();
  // const keyWord = useSelector((state: RootState) => state.search);

  // 입력 폼 변화 감지하여 입력 값 관리
  const [Value, setValue] = useState("");
  // 제출한 검색어 관리
  // const [Keyword, setKeyword] = useState("");

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
    dispatch(inputKeyword(Value));
    if (Value == "") {
      Swal.fire({
        title: "검색어를 입력해주세요.",
      });
    }
  };
  return (
    <SearchBarWrapper>
      <SearchForm onSubmit={submitKeyword}>
        <input
          type="text"
          placeholder="장소를 검색하세요"
          onChange={keywordChange}
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
