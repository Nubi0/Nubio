import { useState } from "react";
import { RecommendPurposeWrapper, PurposeWrapper, Purposes } from "../../../styles/SRecommendPage";
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';
import PurposeItem from "../common/PurposeItem";

const RecommendPurpose = () => {
    const people = [
        {
            value: 0,
            label: '1명',
        },
        {
          value: 25,
          label: '2명',
        },
        {
          value: 50,
          label: '3명',
        },
        {
          value: 75,
          label: '4명',
        },
        {
          value: 100,
          label: '그 이상',
        },
      ];

    const relation = [
        {
            value: 0,
            label: '연인'
        },
        {
            value: 50,
            label: '친구'
        },
        {
            value: 100,
            label: '가족'
        },
    ]

    const age = [
        {
            value: 0,
            label: '10대'
        },
        {
            value: 25,
            label: '20대'
        },
        {
            value: 50,
            label: '30대'
        },
        {
            value: 75,
            label: '40대'
        },
        {
            value: 100,
            label: '50대 이상'
        },
    ]

    const pursposes = ["드라이빙", "산책", "인생샷 찍기", "여행"]


    const customStyles = {
        color: "#F9373F",
        height: 2, // Height in pixels
        '& .MuiSlider-thumb': {
          height: 16, // Thumb height
          width: 16, // Thumb width
          backgroundColor: '#F9373F',
          border: '2px solid currentColor',
          '&:focus, &:hover, &.Mui-active, &.Mui-focusVisible': {
            boxShadow: 'inherit',
          },
          '&:before': {
            display: 'none',
          },
          '& .MuiSlider-markLabel': {
            '& .MuiTypography-root': {
              // 커스텀 클래스를 적용하여 스타일 변경
              fontSize: '12px !important',
              fontWeight: '800',
            },
          },
        },
    };

    const [selectedPurposes, setSelectedPurposes] = useState<string[]>([]);

    // 목적을 선택할 때 호출되는 함수
    const handlePurposeClick = (purpose: string) => {
      if (!selectedPurposes.includes(purpose)) {
        // 목적이 이미 선택되지 않았다면 추가
        setSelectedPurposes([...selectedPurposes, purpose]);
      } else {
        // 이미 선택된 목적이면 제거
        const updatedPurposes = selectedPurposes.filter((item) => item !== purpose);
        setSelectedPurposes(updatedPurposes);
      }
    };


    return(
        <RecommendPurposeWrapper>
            <div>
                인원수
                <Box style={{ padding: '0.5rem'}}>
                    <Slider
                        sx={customStyles}
                        defaultValue={0}
                        step={25}
                        marks={people}
                        valueLabelDisplay="off"
                    />
                </Box>
            </div>
            <div>
                관계
                <Box style={{ padding: '0.5rem'}}>
                    <Slider
                        sx={customStyles}
                        defaultValue={0}
                        step={50}
                        marks={relation}
                    />
                </Box>
            </div>
            <div>
                나이
                <Box style={{ padding: '0.5rem'}}>
                    <Slider
                        sx={customStyles}
                        defaultValue={0}
                        step={25}
                        marks={age}
                    />
                </Box>
            </div>
            <PurposeWrapper>
                <div>
                    목적
                </div>
                <Purposes>
                {pursposes.map((purpose:string, index:number) => {
                    const isSelected = selectedPurposes.includes(purpose);
                    return(
                        <PurposeItem purpose={purpose} key={index} handleClick={() => handlePurposeClick(purpose)} isSelected={isSelected} />
                    )
                })}
                </Purposes>
            </PurposeWrapper>
        </RecommendPurposeWrapper>
    )
};

export default RecommendPurpose;