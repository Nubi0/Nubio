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

type placeProps = {
  place: {
    id: string;
    address_name: string;
    category_group_code: string;
    category_group_name: string;
    phone: string;
    place_name: string;
    place_url: string;
    img_url: string;
    road_address_name: string;
    x: string;
    y: string;
    sequence: number;
  };
};

const CourseDetailItem = ({ place }: placeProps) => {
  const { sequence, address_name, category_group_name, place_name, img_url } = place;
  const place_pin = process.env.PUBLIC_URL + '/assets/place_pin.svg';

  return (
    <CourseDetailItemWrapper>
      <PlaceImg src={img_url} alt="" />
      <InfoWrapper>
        <Left>{sequence + 1}</Left>
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
        <button>상세 보기</button>
      </DetailButton>
    </CourseDetailItemWrapper>
  );
};

export default CourseDetailItem;
