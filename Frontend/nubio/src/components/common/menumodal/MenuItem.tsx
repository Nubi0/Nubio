import { MenuItemWrapper } from "../../../styles/SFooter";

const MenuItem = ({name}: {name: string}) => {
    return(
        <MenuItemWrapper>
            <div>{name}</div>
        </MenuItemWrapper>
    )
}

export default MenuItem;