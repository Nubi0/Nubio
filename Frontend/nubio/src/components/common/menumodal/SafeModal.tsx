import { SafeModalWrapper, ModalTitle, MenuList } from "../../../styles/SFooter";
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';
import MenuItem from "./MenuItem";
import CloseButton from './CloseButton'

const SafeModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const Menus = ['재난 문자 조회', '제보 기능', '길 찾기'];
    return (
        <SafeModalWrapper>
            <ModalTitle>
                <h2>안전</h2>
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
        </SafeModalWrapper>
    )
}

export default SafeModal;