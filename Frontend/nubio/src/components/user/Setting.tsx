import { useNavigate } from "react-router-dom";
import { SettingWrapper, MyRefrenceWrapper, MyListWrapper, LogOutWrapper, DeleteUserWrapper } from "../../styles/SProfilePage";
import Swal from "sweetalert2";
import axios from "axios";

const Setting = ({openModal}: any) => {
    const navigate = useNavigate();


    // 로그아웃 클릭 시
    const openLogOut = () => {
        Swal.fire({
        position: "center",
        title: "로그아웃 하시겠습니까?",
        text: "NUBIO",
        showConfirmButton: true,
        showCancelButton: true,
        confirmButtonText: "예",
        cancelButtonText: "아니요",
        color: "black",
        }).then((res) => {
          if(res.isConfirmed){
            axios.post('https://nubi0.com/auth/v1/member/logout')
                  .then((res) => {
                    navigate('/');
                    localStorage.clear();
                  })
                  .catch((err) => {
                    console.error(err);
                  })
          }
        })
    };
    // 회원 탈퇴 클릭시
    const openDeleteUser = () => {
        Swal.fire({
        position: "center",
        title: "회원탈퇴 하시겠습니까?",
        text: "NUBIO",
        showConfirmButton: true,
        showCancelButton: true,
        confirmButtonText: "예",
        cancelButtonText: "아니요",
        color: "black",
        })
          .then((res) => {
            if(res.isConfirmed){
              axios.patch(process.env.REACT_APP_SERVER_URL + '/auth/v1/member/delete')
                    .then((res) => {
                      Swal.fire({
                        position: 'center',
                        title: '회원탈퇴 성공',
                        text: 'NUBIO',
                        icon: 'success'
                      }).then(() => {
                        localStorage.clear();
                        navigate('/');
                      })
                    })
                    .catch((err) => {
                      console.error(err);
                    })
            }

          })
    };

    return(
        <SettingWrapper>
        <MyRefrenceWrapper>
          <h3 onClick={openModal}>나의 취향</h3>
        </MyRefrenceWrapper>
        <hr />
        <MyListWrapper onClick={() => navigate('/like')}>
          <h3>나의 찜 목록</h3>
        </MyListWrapper>
        <hr />
        <LogOutWrapper onClick={openLogOut}>
          <h3>로그아웃</h3>
        </LogOutWrapper>
        <hr />
        <DeleteUserWrapper onClick={openDeleteUser}>
          <h3>회원탈퇴</h3>
        </DeleteUserWrapper>
      </SettingWrapper>
    )
}

export default Setting;