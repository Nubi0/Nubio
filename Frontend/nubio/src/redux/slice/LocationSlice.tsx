import { createSlice, PayloadAction } from '@reduxjs/toolkit';

// 새로운 데이터 구조에 맞는 타입을 정의합니다
interface ChatClientInfo {
  region: {
    address_name: string;
    region_1depth_name: string;
    region_2depth_name: string;
    region_3depth_name: string;
  };
  emergency_message: {
    city: string;
    county: string;
    message: string;
    md_id: number;
    emer_type: string;
    emer_stage: string;
    occurred_time: string;
  };
  report: {
    title: string;
    content: string;
    latitude: number;
    longitude: number;
    report_id: number;
    report_type: string;
    create_time: string;
    report_files: {
      img_url: string[];
    };
  };
}

interface ChatRoomInfo {
  room_id: number;
  title: string;
  room_type: string;
  profile_url: string;
  members: Array<{
    id: number;
    member_id: string;
    profile_url: string;
    last_read_msg_id: number | null;
    active: boolean;
    role: string;
    nickname: string;
  }>;
  sido_name: string;
  sgg_name: string;
  umd_name: string;
  ri_name: string;
  exist_member_num: number;
}

interface LocationState {
  chatClient: ChatClientInfo;
  chattingRoom: ChatRoomInfo;
}

// 초기 상태를 설정합니다
const initialState: LocationState = {
    chatClient: {
      region: {
        address_name: '',
        region_1depth_name: '',
        region_2depth_name: '',
        region_3depth_name: '',
      },
      emergency_message: {
        city: '',
        county: '',
        message: '',
        md_id: 0,
        emer_type: '',
        emer_stage: '',
        occurred_time: '',
      },
      report: {
        title: '',
        content: '',
        latitude: 0,
        longitude: 0,
        report_id: 0,
        report_type: '',
        create_time: '',
        report_files: {
          img_url: [],
        },
      },
    },
    chattingRoom: {
      room_id: 0,
      title: '',
      room_type: '',
      profile_url: '',
      members: [],
      sido_name: '',
      sgg_name: '',
      umd_name: '',
      ri_name: '',
      exist_member_num: 0,
    },
  };

const locationSlice = createSlice({
  name: 'location',
  initialState,
  reducers: {
    // 서버로부터 받은 데이터를 저장하는 액션
    setChatClientInfo: (state, action: PayloadAction<ChatClientInfo>) => {
      state.chatClient = action.payload;
    },
    setChatRoomInfo: (state, action: PayloadAction<ChatRoomInfo>) => {
      state.chattingRoom = action.payload;
    },
  },
});

// 생성된 액션들을 export 합니다
export const { setChatClientInfo, setChatRoomInfo } = locationSlice.actions;

export default locationSlice.reducer;
