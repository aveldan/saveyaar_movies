import { StyleSheet } from "react-native";
import Scale from "@/constants/Scale";

export const styles = StyleSheet.create({
    container: {
        flex: 1,
        position: 'relative',
        backgroundColor: 'transparent',
    },
    scrollView: {
        flex: 1,
        paddingTop: 120,
    },
    scrollViewContent: {
        paddingBottom: 100,
        paddingTop: 20,
    },
    header: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        zIndex: 1000,
    },
    headerTitleContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingHorizontal: 16,
        height: 50,
        paddingTop: 40,
    },
    headerTitle: {
        color: '#fff',
        fontSize: 24,
        fontWeight: 'bold',
    },
    headerButtons: {
        flexDirection: 'row',
        gap: 4,
    },
    blurContainer: {
        width: '100%',
    },
    profileImg: {
        height: Scale(40),
        width: Scale(40),
        borderRadius: Scale(20),
    },
    profileBorder: {
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: '#9C7CDE',
        height: Scale(44),
        width: Scale(44),
        borderRadius: Scale(22),
    },
});