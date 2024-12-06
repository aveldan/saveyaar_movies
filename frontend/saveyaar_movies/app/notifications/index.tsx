import TabScreenWrapper from "@/components/TabScreenWrapper";
import React from "react";
import { usePathname } from "expo-router";
import HeaderComponent from "@/components/HeaderComponent";
import { ScrollView, View } from "react-native";
import notiData from "@/data/notifications.json";
import NotificationComponent, { NotificationData } from "@/components/NotificationComponent";
import { styles } from "@/styles/notification";

export default function Notifications() {
    
    const pathname = usePathname();
    const isActive = pathname === "/notifications";

    const { notifications } = notiData as NotificationData;

    return (
        <TabScreenWrapper isActive={isActive} slideDirection="left">
            <HeaderComponent title="Notifications" />
            <ScrollView style={styles.container}>
                {notifications.map((notification) => (
                    <NotificationComponent
                        imageUrl={notification.imageUrl}
                        title={notification.title}
                        message={notification.message}
                        rating={notification.rating}       
                        key={notification.title}                 
                    />
                ))}
            </ScrollView>
        </TabScreenWrapper>
    );
}