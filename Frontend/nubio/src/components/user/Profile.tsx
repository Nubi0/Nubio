import { ProfileWrapper } from "../../styles/SProfilePage";
import Setting from "./Setting";
import UserInfo from "./UserInfo";
import { useDispatch } from "react-redux";
import { useEffect } from 'react';
import axios from "axios";
import { setEmail, setNewNickName, setProfileUrl } from "../../redux/slice/Profileslice";

const Profile = ({openModal}: any) => {
    const dispatch = useDispatch();

    useEffect(() => {
      axios.get(process.env.REACT_APP_SERVER_URL + '/auth/v1/member/me')
            .then((res) => {
              const { nickname, profileUrl, email, identification } = res.data.data;
              dispatch(setEmail(email));
              dispatch(setProfileUrl(profileUrl));
              dispatch(setNewNickName(nickname));  
              localStorage.setItem('identification', identification);
            })
            .catch((err) => {
              console.error(err);
            })
    }, []);
    return(
        <ProfileWrapper>
            <UserInfo  />
            <Setting openModal={openModal} />
        </ProfileWrapper>
    );
}

export default Profile;