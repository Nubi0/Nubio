import { PlaceListWrapper } from "@styles/SLikePage";
import PlaceItem from "./PlaceItem";

const PlaceList = () => {
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
  ];
  return (
    <PlaceListWrapper>
      {place_list.map((place, index) => {
        return <PlaceItem key={index} place={place} />;
      })}
    </PlaceListWrapper>
  );
};

export default PlaceList;
