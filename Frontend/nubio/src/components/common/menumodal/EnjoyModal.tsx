import { ModalTitle, MenuList, EnjoyModalWrapper, EnjoyItem } from "../../../styles/SFooter"
import MenuItem from "./MenuItem"
import CloseButton from "./CloseButton"
import { useNavigate } from "react-router-dom";

const EnjoyModal = ({setActive}: {setActive: (value: boolean) => void}) => {
    const navigate = useNavigate();
    const Menus = [
        {
            name: '코스 조회',
            func: () => navigate('/enjoy')
        },
        {
            name: '코스 생성',
            func: () => navigate('/mycourse'),
        }
    ];
    const enjoyImg = process.env.PUBLIC_URL + '/assets/menu/menuEnjoy.png';

    return(
        <EnjoyModalWrapper>
            <ModalTitle>
                <h2 style={{margin: '0'}}>흥미</h2>
                <img src={enjoyImg} style={{ width: "3.5rem", height: "3.5rem" }} />
            </ModalTitle>
            <MenuList>
                {Menus.map((value, index) => {
                    return (
                        <EnjoyItem onClick={value.func}>
                            <MenuItem key={index} name={value.name} />
                        </EnjoyItem>
                    )
                })}
            </MenuList>
            <CloseButton setModal={() => setActive(false)} />
        </EnjoyModalWrapper>
    )
}

export default EnjoyModal;