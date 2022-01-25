/* Walkin v1.0
 * Hotel Management Software
 * FC Solutions - Software Division
 * 22/Feb/2010
 * Azores
 */
package walkin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import java.awt.Polygon;

/**
 * Planning chart.
 * @author Joao Paulo
 */
public class panePlanning extends PCanvas {

    private static int[][] BOOKING_OCUP = {
        /*
         * 0 - nothing
         * 1 - half a day
         * 2 - whole day
         *
         * -------------------------- days ----------------------- */
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static int[][] BOOKING_KIND = {
        /*
         * 0 - nothing
         * 1 - checkin
         * 2 - checkout
         * 3 - waiting
         *
         * -------------------------- days ----------------------- */
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    /* number of days to display. */
    static int DEFAULT_DISPLAY_NUM_OF_DAYS = 15;

    /* number of rooms to display. */
    static int DEFAULT_DISPLAY_NUM_OF_ROOMS = 5;

    /* number of pixels to offset horizontally and vertically, 
     * when displaying text inside the boxes. 
     */
    static int TEXT_X_OFFSET = 1;
    static int TEXT_Y_OFFSET = 10;

    /* offset, pixel wise, when filling the box. */
    static int FILLED_BOX_X_OFFSET = 1;
    static int FILLED_BOX_Y_OFFSET = 1;


    
    private PlanningNode planningNode;

    private int startDate = 2;
    private int startRoom = 2;

    public panePlanning() {
        planningNode = new PlanningNode();
        getLayer().addChild(planningNode);
        setZoomEventHandler(null);
        setPanEventHandler(null);
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent arg0) {
                planningNode.setBounds(getX(), getY(), getWidth() - 1, getHeight() - 1);
                planningNode.layoutChildren(false);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }

    /**
     * Planning.
     * Set of rooms and their booking information.
     */
    static class PlanningNode extends PNode {

        static int DEFAULT_ANIMATION_MILLIS = 250;
        static float FOCUS_SIZE_PERCENT = 0.65f;
        static Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 10);
        int numDisplayedRooms = DEFAULT_DISPLAY_NUM_OF_DAYS;
        int numDisplayedDays = DEFAULT_DISPLAY_NUM_OF_ROOMS;
        int daysExpanded = 0;
        int weeksExpanded = 0;

        public PlanningNode() {

            /*
             * First, we add the nodes, generating a grid:
             * 
             *           ROOMS >< DAYS
             * 
             */
            for (int days = 0; days < numDisplayedDays; days++) {
                for (int rooms = 0; rooms < numDisplayedRooms; rooms++) {
                    addChild(new RoomNode(days, rooms, BOOKING_OCUP[days][rooms], BOOKING_KIND[days][rooms]));
                }
            }

            /*
             * Add a event listener for when the user clicks on a room/room.
             */
            PlanningNode.this.addInputEventListener(new PBasicInputEventHandler() {

                @Override
                public void mouseReleased(PInputEvent event) {
                    RoomNode pickedDay = (RoomNode) event.getPickedNode();
                    if (pickedDay.hasWidthFocus() && pickedDay.hasHeightFocus()) {
                        setFocusDay(null, true);
                    } else {
                        setFocusDay(pickedDay, true);
                    }
                }
            });
        }

        public RoomNode getDay(int day, int room) {
            return (RoomNode) getChild((day * numDisplayedRooms) + room);
        }

        public void setFocusDay(RoomNode focusDay, boolean animate) {

            /*
             * remove width & height focus for every room node.
             */
            for (int i = 0; i < getChildrenCount(); i++) {
                RoomNode each = (RoomNode) getChild(i);
                each.setHasWidthFocus(false);
                each.setHasHeightFocus(false);
            }

            if (focusDay == null) {
                daysExpanded = 0;
                weeksExpanded = 0;
            } else {
                focusDay.setHasWidthFocus(true);
                daysExpanded = 1;
                weeksExpanded = 1;

                for (int i = 0; i < numDisplayedRooms; i++) {
                    getDay(focusDay.getDay(), i).setHasHeightFocus(true);
                }

                for (int i = 0; i < numDisplayedDays; i++) {
                    getDay(i, focusDay.getRoom()).setHasWidthFocus(true);
                }
            }

            layoutChildren(animate);
        }

        protected void layoutChildren(boolean animate) {
            double focusWidth = 0;
            double focusHeight = 0;

            if (daysExpanded != 0 && weeksExpanded != 0) {
                focusWidth = (getWidth() * FOCUS_SIZE_PERCENT) / daysExpanded;
                focusHeight = (getHeight() * FOCUS_SIZE_PERCENT) / weeksExpanded;
            }

            double collapsedWidth = (getWidth() - (focusWidth * daysExpanded)) / (numDisplayedRooms - daysExpanded);
            double collapsedHeight = (getHeight() - (focusHeight * weeksExpanded)) / (numDisplayedDays - weeksExpanded);

            double xOffset = 0;
            double yOffset = 0;
            double rowHeight = 0;
            RoomNode each = null;

            for (int week = 0; week < numDisplayedDays; week++) {
                for (int day = 0; day < numDisplayedRooms; day++) {
                    each = getDay(week, day);
                    double width = collapsedWidth;
                    double height = collapsedHeight;

                    if (each.hasWidthFocus()) {
                        width = focusWidth;
                    }
                    if (each.hasHeightFocus()) {
                        height = focusHeight;
                    }

                    if (animate) {
                        each.animateToBounds(xOffset, yOffset, width, height, DEFAULT_ANIMATION_MILLIS).setStepRate(0);
                    } else {
                        each.setBounds(xOffset, yOffset, width, height);
                    }

                    xOffset += width;
                    rowHeight = height;
                }
                xOffset = 0;
                yOffset += rowHeight;
            }
        }
    }

    static class RoomNode extends PNode {

        /* Information that identifies the room. */
        private long roomID;
        private String roomNumber;
        private String roomType;
        boolean hasWidthFocus;
        boolean hasHeightFocus;
        ArrayList lines;
        int day;
        int room;
        int ocupKind;
        int bookingKind;
        String dayOfMonthString;

        public RoomNode(int dayIx, int roomIx, int ocupKind, int bookingKind) {
            lines = new ArrayList();
            lines.add("7:00 AM Walk the dog.");
            lines.add("9:30 AM Meet John for Breakfast.");
            lines.add("12:00 PM Lunch with Peter.");
            lines.add("3:00 PM Research Demo.");
            lines.add("6:00 PM Pickup Sarah from gymnastics.");
            lines.add("7:00 PM Pickup Tommy from karate.");
            this.day = dayIx;
            this.room = roomIx;
            this.ocupKind = ocupKind;
            this.bookingKind = bookingKind;
            this.dayOfMonthString = Integer.toString((dayIx * 7) + roomIx);
            setPaint(Color.BLACK);
        }

        public int getDay() {
            return day;
        }

        public int getRoom() {
            return room;
        }

        public boolean hasHeightFocus() {
            return hasHeightFocus;
        }

        public void setHasHeightFocus(boolean hasHeightFocus) {
            this.hasHeightFocus = hasHeightFocus;
        }

        public boolean hasWidthFocus() {
            return hasWidthFocus;
        }

        public void setHasWidthFocus(boolean hasWidthFocus) {
            this.hasWidthFocus = hasWidthFocus;
        }

        @Override
        protected void paint(PPaintContext paintContext) {
            Graphics2D g2 = paintContext.getGraphics();
            g2.setPaint(getPaint());
            g2.draw(getBoundsReference());
            g2.setFont(PlanningNode.DEFAULT_FONT);

            /* set background based on the state of the booking. */
            switch (this.bookingKind) {
                case 0:
                    g2.setBackground(Color.LIGHT_GRAY);
                    break;
                case 1:
                    g2.setBackground(Color.yellow);
                    break;
                case 2:
                    g2.setBackground(Color.green);
                    break;
                case 3:
                    g2.setBackground(Color.red);
                    break;
                default:
                    break;
            }

            PBounds bounds = getBounds();


            Polygon leftHalf = new Polygon();


            paintContext.pushClip(getBoundsReference());
            g2.clearRect((int) bounds.getX() + panePlanning.FILLED_BOX_X_OFFSET,
                         (int) bounds.getY() + panePlanning.FILLED_BOX_Y_OFFSET,
                         (int) bounds.getWidth() - panePlanning.FILLED_BOX_X_OFFSET,
                         (int) bounds.getHeight() - panePlanning.FILLED_BOX_Y_OFFSET);
            
            paintContext.popClip(getBoundsReference());

            float y = (float) getY() + panePlanning.TEXT_Y_OFFSET;

                            
            /*paintContext.getGraphics().drawString(dayOfMonthString, (float) getX() + PlanningNode.TEXT_X_OFFSET, y);*/

            if (hasWidthFocus && hasHeightFocus) {
                paintContext.pushClip(getBoundsReference());
                
                for (int i = 0; i < lines.size(); i++) {
                    y += 10;
                    g2.drawString((String) lines.get(i), (float) getX() + panePlanning.TEXT_X_OFFSET, y);
                }
                paintContext.popClip(getBoundsReference());
            }
        }
    }
}
