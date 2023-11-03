type ReportItem = {
    reportId: number;
    identificationFlag: boolean;
    region: string;
    title: string;
    content: string;
    reportType: string;
    latitude: number;
    longitude: number;
    fileList: {
        fileUrl: String[];
    };
    create_time: Date;
  }