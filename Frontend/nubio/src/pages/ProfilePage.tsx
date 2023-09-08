import {
  ProfileWrapper,
  MyInfoWrapper,
  SettingWrapper,
  LogOutWrapper,
  DeleteUserWrapper,
  MyListWrapper,
  MyRefrenceWrapper,
} from "../styles/SProfilePage";

const ProfilePage = () => {
  const user = process.env.PUBLIC_URL + "/assets/user.png";
  return (
    <ProfileWrapper>
      <MyInfoWrapper>
        <h1>내 정보</h1>
        <img src={user} alt="" />
        <h2>이메일</h2>
        <h2>이름</h2>
        <h2>닉네임</h2>
      </MyInfoWrapper>

      <SettingWrapper>
        <MyRefrenceWrapper>
          <h1>나의 취향</h1>
        </MyRefrenceWrapper>
        <MyListWrapper>
          <h1>나의 찜 목록</h1>
        </MyListWrapper>
        <LogOutWrapper>
          <h1>로그아웃</h1>
        </LogOutWrapper>
        <DeleteUserWrapper>
          <h1>회원탈퇴</h1>
        </DeleteUserWrapper>
      </SettingWrapper>
    </ProfileWrapper>
  );
};

export default ProfilePage;

