import { CategoryWrapper } from "../../../styles/SAllCoursePage";

const Category = ({active, handleButton} : {active: activeProps, handleButton: (category: string) => void}) => {
    return(
        <CategoryWrapper>
            <div onClick={() => handleButton('all')} className={active.all ? 'active' : ''}>전체</div>
            <div onClick={() => handleButton('popular')} className={active.popular ? 'active' : ''}>인기</div>
        </CategoryWrapper>
    );
}

export default Category;