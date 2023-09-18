import { ProfileWrapper } from "../../styles/SProfilePage";
import Setting from "./Setting";
import UserInfo from "./UserInfo";

const Profile = ({openModal}: any) => {
    return(
        <ProfileWrapper>
            <UserInfo  />
            <Setting openModal={openModal} />
        </ProfileWrapper>
    );
}

export default Profile;