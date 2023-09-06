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