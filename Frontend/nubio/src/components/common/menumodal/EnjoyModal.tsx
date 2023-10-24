import { ModalTitle, MenuList, EnjoyModalWrapper } from "../../../styles/SFooter"
import MenuItem from "./MenuItem"
import CloseButton from "./CloseButton"
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';

const EnjoyModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const Menus = ['코스 조회', '코스 생성'];
    return(
        <EnjoyModalWrapper>
            <ModalTitle>
                <h2>흥미</h2>
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
        </EnjoyModalWrapper>
    )
}

export default EnjoyModal;