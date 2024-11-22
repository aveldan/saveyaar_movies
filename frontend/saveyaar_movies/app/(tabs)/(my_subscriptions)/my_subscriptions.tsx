import React from "react";
import { StyleSheet, View } from "react-native";
import TabScreenWrapper from "../../../components/TabScreenWrapper";
import { usePathname } from "expo-router";


export default function MySubscriptionsScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/my_subscriptions";
	
	return (
		<TabScreenWrapper isActive={isActive} slideDirection="left">
			<View style={styles.container}>
				<View style={styles.square} />
			</View>
		</TabScreenWrapper>
	);
};

const styles = StyleSheet.create({
	container: {
        flex: 1, // Occupy the entire screen
        justifyContent: "center", // Center vertically
        alignItems: "center", // Center horizontally
    },
    square: {
        width: 100, // Square's width
        height: 100, // Square's height
        backgroundColor: "yellow", // Square's color
    },
});