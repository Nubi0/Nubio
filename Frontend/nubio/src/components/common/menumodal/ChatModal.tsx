import { ModalTitle, MenuList, ChatModalWrapper } from "../../../styles/SFooter"
import CloseButton from "./CloseButton"
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';
import MenuItem from "./MenuItem";

const ChatModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const Menus = ['채팅 시작']
    return(
        <ChatModalWrapper>
             <ModalTitle>
                <h2>채팅</h2>
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
        </ChatModalWrapper>
    )
}

export default ChatModal;