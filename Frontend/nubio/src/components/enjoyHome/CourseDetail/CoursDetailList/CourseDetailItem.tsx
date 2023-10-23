import {
  CourseDetailItemWrapper,
  PlaceImg,
  InfoWrapper,
  Left,
  Right,
  PlaceName,
  Address,
  CategoryName,
  DetailButton,
} from '../../../../styles/SCourseDeatilPage';

const CourseDetailItem = ({ place }: {place: placeProps}) => {
  const {
    sequence,
    address_name,
    category_group_name,
    place_name,
    place_url,
    x,
    y,
  } = place;
  const place_pin = process.env.PUBLIC_URL + '/assets/place_pin.svg';
  const noImage = process.env.PUBLIC_URL + '/assets/noImage.png';
  const marker = new kakao.maps.Marker({
    map: window.map,
    position: new kakao.maps.LatLng(Number(y), Number(x)),
  });

  return (
    <CourseDetailItemWrapper>
      <PlaceImg src={noImage} alt="" />
      <InfoWrapper>
        <Left>{sequence}</Left>
        <Right>
          <CategoryName>{category_group_name}</CategoryName>
          <PlaceName>{place_name}</PlaceName>
          <Address>
            <img src={place_pin} alt="" />
            <div>{address_name}</div>
          </Address>
        </Right>
      </InfoWrapper>
      <DetailButton>
        <a href={place_url}>상세 보기</a>
      </DetailButton>
    </CourseDetailItemWrapper>
  );
};

export default CourseDetailItem;
