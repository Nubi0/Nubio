import { useState} from "react";
import { PlaceReviewWrapper, ReviewHeader, ReviewForm } from "../../../../styles/SPlaceDetailPage";
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';

const PlaceReview = () => {
    // const star = process.env.PUBLIC_URL + '/assets/star.svg';
    const [value, setValue] = useState<number | null>(0);
    return(
        <PlaceReviewWrapper>
            <ReviewHeader>
                리뷰
            </ReviewHeader>
            <ReviewForm>
                <input type="text" placeholder="이 장소의 후기를 남겨주세요." />
                <Box>
                    <Rating value={value}
                       onChange={(event, newValue) => {
                        setValue(newValue);
                      }} />
                </Box>
            </ReviewForm>
        </PlaceReviewWrapper>
    )
}

export default PlaceReview;