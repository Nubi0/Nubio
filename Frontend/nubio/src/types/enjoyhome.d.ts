interface CourseItem {
  title: string;
  course_tags: any[];
  favorite_flag: boolean;
  like_count: number;
  place_list: any[];
  course_id: string;
  course_id: number;
}

interface CourseProps {
  value: CourseItem;
}

interface PlaceItem {
  place_id: number;
  kakao_id: number;
  place_name: string;
  img_url: string;
}

interface PageName {
  pageName: string;
}
