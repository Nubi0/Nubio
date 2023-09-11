import { useState } from "react";
import { SearchResultWrapper } from "../../../../styles/SSearchBar";
import DetailItem from "../detailSearch/DetailItem";
import SearchItem from "./SearchItem";

const SearchList = () => {
  const place_list = [
    {
      id: "1",
      address_name: "경기 수원시 팔달구 인계동 1113-11",
      category_group_code: "CT1",
      category_group_name: "문화시설",
      phone: "1544-1122",
      place_name: "CGV 동수원",
      place_url: "http://place.map.kakao.com/8552500",
      road_address_name: "경기 수원시 팔달구 권광로 181",
      img_url: process.env.PUBLIC_URL + "/assets/cgv.jpg",
      x: "127.03231003231717",
      y: "37.26390632559984",
    },
    {
      id: "2",
      address_name: "경기 수원시 팔달구 인계동 1113-11",
      category_group_code: "CT1",
      category_group_name: "문화시설",
      phone: "1544-1122",
      place_name: "CGV 동수원",
      place_url: "http://place.map.kakao.com/8552500",
      road_address_name: "경기 수원시 팔달구 권광로 181",
      img_url: process.env.PUBLIC_URL + "/assets/cgv.jpg",
      x: "127.03231003231717",
      y: "37.26390632559984",
    },
    {
      id: "3",
      address_name: "경기 수원시 팔달구 인계동 1113-11",
      category_group_code: "CT1",
      category_group_name: "문화시설",
      phone: "1544-1122",
      place_name: "CGV 동수원",
      place_url: "http://place.map.kakao.com/8552500",
      road_address_name: "경기 수원시 팔달구 권광로 181",
      img_url: process.env.PUBLIC_URL + "/assets/cgv.jpg",
      x: "127.03231003231717",
      y: "37.26390632559984",
    },
    {
      id: "4",
      address_name: "경기 수원시 팔달구 인계동 1113-11",
      category_group_code: "CT1",
      category_group_name: "문화시설",
      phone: "1544-1122",
      place_name: "CGV 동수원",
      place_url: "http://place.map.kakao.com/8552500",
      road_address_name: "경기 수원시 팔달구 권광로 181",
      img_url: process.env.PUBLIC_URL + "/assets/cgv.jpg",
      x: "127.03231003231717",
      y: "37.26390632559984",
    },
    {
      id: "5",
      address_name: "경기 수원시 팔달구 인계동 1113-11",
      category_group_code: "CT1",
      category_group_name: "문화시설",
      phone: "1544-1122",
      place_name: "CGV 동수원",
      place_url: "http://place.map.kakao.com/8552500",
      road_address_name: "경기 수원시 팔달구 권광로 181",
      img_url: process.env.PUBLIC_URL + "/assets/cgv.jpg",
      x: "127.03231003231717",
      y: "37.26390632559984",
    },
  ];
  // 장소 상세보기
  const [isDetail, setIsDetail] = useState(false);
  const detailPlace = () => {
    setIsDetail(true);
  };
  return (
    <SearchResultWrapper>
      {place_list.map((place, index) => {
        return (
          <>
            <SearchItem key={index} place={place} />
            <DetailItem key={index} onClick={detailPlace} place={place} />
          </>
        );
      })}
    </SearchResultWrapper>
  );
};

export default SearchList;
