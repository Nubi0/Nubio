import HomePage from "../pages/HomePage";
import LoginPage from "../pages/LoginPage";
import EnjoyHomePage from "../pages/EnjoyHomePage";
import SignUpPage from "../pages/SignUpPage";
import AllCoursePage from "../pages/AllCoursePage";
import ProfilePage from "../pages/ProfilePage";
import CourseDetailPage from "../pages/CourseDeatilPage";
import LikePage from "../pages/LikePage";
import MyCoursePage from "../pages/MyCoursePage";
import SafeHomePage from "../pages/SafeHomePage";
import CourseSelectPage from "../pages/CourseSelectPage";
import KaKaoLogin from "../pages/KaKaoLogin";
import CourseLocationSelectPage from "../pages/CourseLocationSelectPage";
import AllCourseLocationSelectPage from "../pages/AllCourseLocationSelectPage";
import ChatRoom from "../pages/ChatroomPage";
import AdminPage from "../pages/AdminPage";
const routes = [
  {
    path: "/",
    Component: HomePage,
  },
  {
    path: "/login",
    Component: LoginPage,
  },
  {
    path: "/enjoy",
    Component: EnjoyHomePage,
  },
  {
    path: "/signUp",
    Component: SignUpPage,
  },
  {
    path: "/enjoy/all",
    Component: AllCoursePage,
  },
  {
    path: "/profile",
    Component: ProfilePage,
  },
  {
    path: "/coursedetail/:courseId",
    Component: CourseDetailPage,
  },
  {
    path: "/like",
    Component: LikePage,
  },
  {
    path: "/mycourse",
    Component: MyCoursePage,
  },
  {
    path: "/safe",
    Component: SafeHomePage,
  },
  {
    path: "enjoy/mycourse/select",
    Component: CourseSelectPage,
  },
  {
    path: "/oauth/kakao",
    Component: KaKaoLogin,
  },
  {
    path: "/enjoy/mycourse/location/select",
    Component: CourseLocationSelectPage,
  },
  {
    path: "/enjoy/all/location",
    Component: AllCourseLocationSelectPage,
  },
  {
    path: "/safe/chatroom",
    Component: ChatRoom,
  },
  {
    path: "/admin",
    Component: AdminPage,
  },
];

export default routes;
