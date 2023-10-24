import { ModalTitle, MenuList, ProfileMoalWrapper } from "../../../styles/SFooter"
import CloseButton from "./CloseButton"
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';
import MenuItem from "./MenuItem";

const ProfileModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const Menus = ['프로필 조회']
    return(
        <ProfileMoalWrapper>
            <ModalTitle>
                <h2>프로필</h2>
                <VerifiedUserIcon style={{ width: '3.5rem', height: '3.5rem'}} />
            </ModalTitle>
            <MenuList>
                {Menus.map((value, index) => {
                    return (
                        <MenuItem name={value} />
                    )
                })}
            </MenuList>
            <CloseButton setModal={() => setActive(false)} />
        </ProfileMoalWrapper>
    )
}

export default ProfileModal;