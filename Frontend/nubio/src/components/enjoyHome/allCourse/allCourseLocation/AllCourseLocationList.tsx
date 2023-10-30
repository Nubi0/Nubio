import { LocationListWrapper } from "../../../../styles/SMyCoursePage";
import AllCourseLocationItem from "./AllCourseLocationItem";

const AllCourseLocationList = () => {
  const locations = [
    {
      name: "SEOUL",
      url: process.env.PUBLIC_URL + "/assets/course/city/seoul.jpg",
    },
    {
      name: "DAEGU",
      url: process.env.PUBLIC_URL + "/assets/course/city/gumi.jpg",
    },
    {
      name: "GYEONGBUK",
      url: process.env.PUBLIC_URL + "/assets/course/city/daejeon.jpg",
    },
    {
      name: "BUSAN",
      url: process.env.PUBLIC_URL + "/assets/course/city/gwangju.jpg",
    },
    {
      name: "DAEJEON",
      url: process.env.PUBLIC_URL + "/assets/course/city/busan.jpg",
    },
    {
      name: "GWANGJU",
      url: process.env.PUBLIC_URL + "/assets/course/city/gwangju.jpg",
    },
  ];

  return (
    <LocationListWrapper>
      {locations.map((location, index) => {
        return <AllCourseLocationItem key={index} location={location} />;
      })}
    </LocationListWrapper>
  );
};

export default AllCourseLocationList;
