interface CourseItem {
    title: string
    course_tags: Array
    favorite_flag: boolean
    like_count: number
    place_list: Array
};

interface CourseProps {
    value: CourseItem
}

interface PlaceItem {
    place_id: number
    kakao_id: number
    place_name: string
    img_url: string
}

interface PageName {
    pageName: string
}