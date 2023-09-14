import { useState } from "react";
import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Footer from "../components/common/Footer";
import SafeGuideModal from "../components/safeHome/safeGuide/SafeGuideModal";
import SearchList from "../components/common/search/searchList/SearchList";
import { SearchForm, SearchBarWrapper } from "../styles/SSearchBar";
import KakaoMap from "../components/common/map/KakaoMap";
import Swal from "sweetalert2";

declare global {
  interface Window {
    kakao: any;
  }
}
export interface propsType {
  searchKeyword: string;
}

const SafeHomePage = () => {
  // 이미지
  const searchIcon = process.env.PUBLIC_URL + "/assets/searchIcon.svg";
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  // 검색
  const [isSearch, setIsSearch] = useState(false);
  const test = () => {
    setIsSearch(!isSearch);
  };
  // 입력 폼 변화 감지하여 입력 값 관리
  const [Value, setValue] = useState("");
  // 제출한 검색어 관리
  const [Keyword, setKeyword] = useState("");

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
    setKeyword(Value);
    if (Value == "") {
      Swal.fire({
        title: "검색어를 입력해주세요.",
      });
    }
  };

  // 검색어를 입력하지 않고 검색 버튼을 눌렀을 경우
  const valueChecker = () => {
    if (Value === "") {
      alert("검색어를 입력해주세요.");
    }
  };
  // 카카오 맵
  // useEffect(() => {
  //   let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
  //   let options = {
  //     //지도를 생성할 때 필요한 기본 옵션
  //     center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
  //     level: 3, //지도의 레벨(확대, 축소 정도)
  //   };
  //   let map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
  // }, []);

  return (
    <SafeHomeWrapper>
      <SafeGuideModal />
      <KakaoMap searchKeyword={Keyword} />
      <LogoIcon src={logo} />
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
      <Footer />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
