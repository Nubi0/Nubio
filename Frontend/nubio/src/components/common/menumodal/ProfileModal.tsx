import { ModalTitle, MenuList, ProfileMoalWrapper, ProfileItem } from "../../../styles/SFooter"
import CloseButton from "./CloseButton"
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';
import MenuItem from "./MenuItem";
import { useNavigate } from "react-router-dom";

const ProfileModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const navigate = useNavigate()
    const Menus = [{
        name: '프로필 조회',
        func: () => navigate('/profile')
    }];
    const profileImg = process.env.PUBLIC_URL + '/assets/menu/menuProfile.png';
    return(
        <ProfileMoalWrapper>
            <ModalTitle>
                <h2>프로필</h2>
                <img src={profileImg} alt="" style={{ width: "3.5rem", height: "3.5rem" }} />
            </ModalTitle>
            <MenuList>
                {Menus.map((value, index) => {
                    return (
                        <ProfileItem onClick={value.func}>
                            <MenuItem name={value.name} />
                        </ProfileItem>
                    )
                })}
            </MenuList>
            <CloseButton setModal={() => setActive(false)} />
        </ProfileMoalWrapper>
    )
}

export default ProfileModal;