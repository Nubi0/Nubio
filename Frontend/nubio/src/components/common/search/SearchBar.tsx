// Hook
import { useDispatch } from "react-redux";
// 라이브러리
import Swal from "sweetalert2";
// 컴포넌트
import MyLocation from "../map/MyLocation";
// 스타일
import { SearchBarWrapper, SearchForm } from "@styles/SMap";
// redux
import { setListIsOpen, setkeyWord } from "@redux/slice/MapSlice";
import useInput from "@hooks/useInput";

const SearchBar = ({ searchPlaces, setFindRouteOpen, removeMarker }: any) => {
  const searchIcon = process.env.PUBLIC_URL + "/assets/search/searchIcon.svg";
  // 검색
  const dispatch = useDispatch();

  // 입력 폼 변화 감지하여 입력 값 관리
  const [Value, onChangeValue] = useInput("");

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
    removeMarker();
  };
  // 검색리스트 보기
  const inputClick = () => {
    dispatch(setListIsOpen(true));
    // searchPlaces(Value);
    // removeMarker();
  };
  return (
    <SearchBarWrapper>
      <SearchForm onSubmit={submitKeyword}>
        <input
          type="text"
          placeholder="장소를 검색하세요"
          onChange={onChangeValue}
          onClick={inputClick}
        />
        <img
          src={searchIcon}
          alt="돋보기"
          className="
readingGlasses"
        />
      </SearchForm>
      <MyLocation />
    </SearchBarWrapper>
  );
};
export default SearchBar;
