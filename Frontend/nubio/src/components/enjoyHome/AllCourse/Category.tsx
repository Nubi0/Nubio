import { useState } from "react";
import { CategoryWrapper } from "../../../styles/SAllCoursePage";

const Category = () => {
    const [active, setActive] = useState({
        all: false,
        popular: false,
    })
    const handleButton = (category: string) => {
        if(category === 'all'){
            setActive({
                all: true,
                popular: false,
            })
        } else if(category === 'popular') {
            setActive({
                all: false,
                popular: true,
            })
        }
    }

    return(
        <CategoryWrapper>
            <div onClick={() => handleButton('all')} className={active.all ? 'active' : ''}>전체</div>
            <div onClick={() => handleButton('popular')} className={active.popular ? 'active' : ''}>인기</div>
        </CategoryWrapper>
    );
}

export default Category;