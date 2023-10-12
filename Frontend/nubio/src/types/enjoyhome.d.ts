type CourseItem = {
  title: string;
  course_tags: string[];
  favorite_flag: boolean;
  like_count: number;
  place_list: PlaceList[];
  course_id: string;
  course_id: number;
}

type PlaceList = {
  place_id: Number;
  kakao_id: Number;
  place_name: string;
  img_url: string;
}

type CourseProps = {
  value: CourseItem;
}

type PlaceItem = {
  place_id: number;
  kakao_id: number;
  place_name: string;
  img_url: string;
}

type PageName = {
  pageName: string;
}

type placeProps = {
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

type reviewProps = {
  content: string;
  course_review_id: number;
  member_id: string;
  nickname: string;
  point: number;
}

type activeProps = {
  all: boolean;
  popular: boolean;
}